import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.controls.Follower
import com.ctre.phoenix6.hardware.TalonFX
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem
import frc.robot.Constants

object Shooter: Subsystem {

    private var topMotor = TalonFX(Constants.Shooter.TOP_MOTOR_CAN_ID).apply {
        configurator.apply(
            TalonFXConfiguration().apply {
                MotorOutput.apply {
                    NeutralMode = NeutralModeValue.Coast
                    Inverted = InvertedValue.CounterClockwise_Positive
                }
            }
        )
    }

    private var bottomMotor = TalonFX(Constants.Shooter.BOTTOM_MOTOR_CAN_ID).apply {
        configurator.apply(
            TalonFXConfiguration().apply {
                MotorOutput.apply {
                    NeutralMode = NeutralModeValue.Coast
                    Inverted = InvertedValue.Clockwise_Positive
                }
            }
        )
    }

    override fun periodic() {}

     fun spinUp(multiplier: Double): Command = startEnd(
        {
            topMotor.setVoltage(Constants.Shooter.TOP_SPIN_UP_BASE_SPEED * multiplier)
            bottomMotor.setVoltage(Constants.Shooter.BOTTOM_SPIN_UP_BASE_SPEED * multiplier)
        },
        {
            topMotor.setVoltage(0.0)
            bottomMotor.setVoltage(0.0)
        }
    )
}