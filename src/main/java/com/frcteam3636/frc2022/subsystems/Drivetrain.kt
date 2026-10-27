import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.controls.Follower
import com.ctre.phoenix6.hardware.TalonFX
import com.ctre.phoenix6.signals.InvertedValue
import com.frcteam3636.frc2022.CTREDeviceId
import com.frcteam3636.frc2022.Constants
import com.frcteam3636.frc2022.utils.LimelightHelpers
import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem
import kotlin.math.abs

object Drivetrain: Subsystem {

    private val leftMotor1 = TalonFX(CTREDeviceId.MOTOR_LEFT_1.ID)
    private val leftMotor2 = TalonFX(CTREDeviceId.MOTOR_LEFT_2.ID)
    private val rightMotor1 = TalonFX(CTREDeviceId.MOTOR_RIGHT_1.ID)
    private val rightMotor2 = TalonFX(CTREDeviceId.MOTOR_RIGHT_2.ID)
    private const val KP = .1
    private const val MIN_TURN = .05
    private val alignController = PIDController(KP, 0.0, 0.0)

//    private val navX: AHRS = AHRS(AHRS.NavXComType.kMXP_SPI)

    init {
        leftMotor2.setControl(Follower(CTREDeviceId.MOTOR_LEFT_1.ID, false)) // TODO: false?
        rightMotor2.setControl(Follower(CTREDeviceId.MOTOR_RIGHT_1.ID, false))

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
            val drive = Constants.Drivetrain.DRIVING_SENSITIVITY * translationJoystick.x // X and Y are flipped, x is y and y is x
            val turn = Constants.Drivetrain.DRIVING_SENSITIVITY * rotationJoystick.y

            rightMotor1.setVoltage(Constants.Drivetrain.BASE_VOLTAGE * (drive + turn))
            leftMotor1.setVoltage(Constants.Drivetrain.BASE_VOLTAGE * (drive - turn))
        }

    fun driveWithController(controller: XboxController): Command =
        run {
            var drive = 0.0
            var turn = 0.0

            if (abs(controller.leftX) >= 0.10) {
                drive = Constants.Drivetrain.TURNING_SENSITIVITY * controller.leftX
            }

            if (abs(controller.rightY) >= 0.10) {
                turn = Constants.Drivetrain.DRIVING_SENSITIVITY * controller.rightY
            }

            rightMotor1.setVoltage(Constants.Drivetrain.BASE_VOLTAGE * (drive + turn))
            leftMotor1.setVoltage(Constants.Drivetrain.BASE_VOLTAGE * (drive - turn))
        }

}