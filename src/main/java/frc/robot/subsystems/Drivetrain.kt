import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.configs.TalonFXConfigurator
import com.ctre.phoenix6.hardware.TalonFX
import com.ctre.phoenix6.signals.InvertedValue
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry
import edu.wpi.first.wpilibj2.command.Subsystem
import frc.robot.Constants

class Drivetrain: Subsystem {

    private val leftMotor1 = TalonFX(Constants.Drivetrain.MOTOR_LEFT_1)
    private val leftMotor2 = TalonFX(Constants.Drivetrain.MOTOR_LEFT_2)
    private val rightMotor1 = TalonFX(Constants.Drivetrain.MOTOR_RIGHT_1)
    private val rightMotor2 = TalonFX(Constants.Drivetrain.MOTOR_RIGHT_2)

    private val odometry = DifferentialDriveOdometry(Rotation2d(), 0.0, 0.0)
    private val navX: AHRS = AHRS()

    init {
        leftMotor1.follow(leftMotor2)
        rightMotor1.follow(rightMotor2)
        leftMotor1.apply {
            configurator.apply {
                TalonFXConfiguration().apply{
                    MotorOutput.apply{
                        Inverted = InvertedValue.Clockwise_Positive
                    }
                }
            }
        }
        leftMotor2.apply {
            configurator.apply {
                TalonFXConfiguration().apply{
                    MotorOutput.apply{
                        Inverted = InvertedValue.Clockwise_Positive
                    }
                }
            }
        }
        rightMotor1.apply {
            configurator.apply {
                TalonFXConfiguration().apply{
                    MotorOutput.apply{
                        Inverted = InvertedValue.CounterClockwise_Positive
                    }
                }
            }
        }
        rightMotor2.apply {
            configurator.apply {
                TalonFXConfiguration().apply{
                    MotorOutput.apply{
                        Inverted = InvertedValue.CounterClockwise_Positive
                    }
                }
            }
        }


    }
}