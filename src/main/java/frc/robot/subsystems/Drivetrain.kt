import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.controls.Follower
import com.ctre.phoenix6.hardware.TalonFX
import com.ctre.phoenix6.signals.InvertedValue
import com.studica.frc.AHRS
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry
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

    private val odometry = DifferentialDriveOdometry(Rotation2d(), 0.0, 0.0)
    private val navX: AHRS = AHRS(AHRS.NavXComType.kMXP_SPI)

    init {
        leftMotor2.setControl(Follower(Constants.Drivetrain.MOTOR_LEFT_1, true)) // TODO: false?
        rightMotor2.setControl(Follower(Constants.Drivetrain.MOTOR_RIGHT_1, true))

        val leftConfig = TalonFXConfiguration().apply{
            MotorOutput.apply{
                Inverted = InvertedValue.Clockwise_Positive
            }
        }

        val rightConfig = TalonFXConfiguration().apply {
            MotorOutput.apply{
                Inverted = InvertedValue.CounterClockwise_Positive
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
            val kp = 0.5
            val minTurn = 0.05

            val steeringAdjust = kp * tx

            if (abs(steeringAdjust) > minTurn) {
                rightMotor1.set(-steeringAdjust)
                leftMotor1.set(steeringAdjust)
            }
        }

    fun moveToTarget() : Command =
        run {
            //TODO()
        }

    fun driveWithJoysticks(translationJoystick: Joystick, rotationJoystick: Joystick): Command =
        run {
            val drive = 0.5 * translationJoystick.x
            val turn = 0.5 * rotationJoystick.y

            rightMotor1.set(drive - turn)
            leftMotor1.set(drive + turn)
        }
}