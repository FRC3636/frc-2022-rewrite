import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.controls.Follower
import com.ctre.phoenix6.hardware.TalonFX
import com.ctre.phoenix6.signals.InvertedValue
import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem
import frc.robot.Constants
import frc.robot.utils.LimelightHelpers
import kotlin.math.abs

object Drivetrain: Subsystem {

    private val leftMotor1 = TalonFX(Constants.Drivetrain.MOTOR_LEFT_1)
    private val leftMotor2 = TalonFX(Constants.Drivetrain.MOTOR_LEFT_2)
    private val rightMotor1 = TalonFX(Constants.Drivetrain.MOTOR_RIGHT_1)
    private val rightMotor2 = TalonFX(Constants.Drivetrain.MOTOR_RIGHT_2)
    private const val KP = .1
    private const val MIN_TURN = .05
    private val alignController = PIDController(KP, 0.0, 0.0)

//    private val navX: AHRS = AHRS(AHRS.NavXComType.kMXP_SPI)

    init {
        leftMotor2.setControl(Follower(Constants.Drivetrain.MOTOR_LEFT_1, false)) // TODO: false?
        rightMotor2.setControl(Follower(Constants.Drivetrain.MOTOR_RIGHT_1, false))

        val leftConfig = TalonFXConfiguration().apply{
            MotorOutput.apply{
                Inverted = InvertedValue.CounterClockwise_Positive
            }
        }

        val rightConfig = TalonFXConfiguration().apply {
            MotorOutput.apply{
                Inverted = InvertedValue.Clockwise_Positive
            }
        }

        leftMotor1.apply {
            configurator.apply {
                leftConfig
            }
        }

        leftMotor2.apply {
            configurator.apply {
                leftConfig
            }
        }

        rightMotor1.apply {
            configurator.apply {
                rightConfig
            }
        }

        rightMotor2.apply {
            configurator.apply {
                rightConfig
            }
        }
    }

    fun alignToTarget() : Command =
        run {
            val tx = LimelightHelpers.getTX("limelight")

            val steeringAdjust = alignController.calculate(tx)

            if (abs(steeringAdjust) > MIN_TURN) {
                rightMotor1.setVoltage(-steeringAdjust)
                leftMotor1.setVoltage(steeringAdjust)
            }
        }

    fun getkP(): Double {
        return KP
    }

    fun moveToTarget() : Command =
        run {
            //TODO()
        }

    fun driveWithJoysticks(translationJoystick: Joystick, rotationJoystick: Joystick): Command =
        run {
            val drive = 0.5 * translationJoystick.x // X and Y are flipped, x is y and y is x
            val turn = 0.5 * rotationJoystick.y

            rightMotor1.setVoltage(12.0 * (drive + turn))
            leftMotor1.setVoltage(12.0 * (drive - turn))
        }

}