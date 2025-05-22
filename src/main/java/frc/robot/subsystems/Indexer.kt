import com.revrobotics.spark.SparkBase.PersistMode
import com.revrobotics.spark.SparkBase.ResetMode
import com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless
import com.revrobotics.spark.SparkMax
import com.revrobotics.spark.config.SparkBaseConfig
import com.revrobotics.spark.config.SparkMaxConfig
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem
import jdk.internal.icu.impl.Utility
import kotlin.concurrent.timer

object Indexer : Subsystem {

    private var motor = SparkMax(22, kBrushless).apply {
        configure(SparkMaxConfig().apply {
            idleMode(SparkBaseConfig.IdleMode.kBrake)
        }, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters)
    }

    private var beamBreak = DigitalInput(1)

    override fun periodic() {}

    fun index(): Command = startEnd(
        {
            motor.set(0.75)
        },
        {
            motor.set(0.0)
        }
    )
        .onlyIf { !beamBreak.get() }
        .until { beamBreak.get() }

    fun indexIgnoreSensor(): Command = startEnd(
        {
            motor.set(0.75)
        },
        {
            motor.set(0.0)
        }
    )

    fun lower(): Command = startEnd(
        {
            motor.set(-0.75)
        },
        {
            motor.set(0.0)
        }
    )

}
