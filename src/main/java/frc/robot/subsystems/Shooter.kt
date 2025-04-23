import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.hardware.TalonFX
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem

object Shooter: Subsystem {

    private var motor = TalonFX(0).apply {
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

    val velocity get() = motor.velocity.value

     fun spinUp(): Command = startEnd(
        {
            motor.set(0.25)
        },
        {
            motor.set(0.0)
        }
    )
}