import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.controls.Follower
import com.ctre.phoenix6.hardware.TalonFX
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem

object Shooter: Subsystem {

    private var topMotor = TalonFX(5).apply {
        configurator.apply(
            TalonFXConfiguration().apply {
                MotorOutput.apply {
                    NeutralMode = NeutralModeValue.Coast
                    Inverted = InvertedValue.CounterClockwise_Positive
                }
            }
        )
    }

    private var bottomMotor = TalonFX(9).apply {
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

    val velocity get() = topMotor.velocity.value

     fun spinUp(multiplier: Double): Command = startEnd(
        {
            topMotor.setVoltage(12.0 * multiplier)
            bottomMotor.setVoltage(10.0 * multiplier)
        },
        {
            topMotor.setVoltage(0.0)
            bottomMotor.setVoltage(0.0)
        }
    )
}