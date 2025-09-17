import com.revrobotics.spark.SparkBase.PersistMode
import com.revrobotics.spark.SparkBase.ResetMode
import com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless
import com.revrobotics.spark.SparkMax
import com.revrobotics.spark.config.SparkBaseConfig
import com.revrobotics.spark.config.SparkMaxConfig
import edu.wpi.first.math.MathUtil.clamp
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem
import frc.robot.Constants

object Indexer : Subsystem {

    private var motor = SparkMax(Constants.Indexer.MOTOR_CAN_ID, kBrushless).apply {
        configure(SparkMaxConfig().apply {
            idleMode(SparkBaseConfig.IdleMode.kBrake)
        }, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters)
    }

    private var beamBreak = DigitalInput(Constants.Indexer.BEAM_BREAK_PORT)

    override fun periodic() {}

    fun index(useSensor: Boolean = false): Command = startEnd(
        {

            motor.set(clamp(Constants.Indexer.INDEX_SPEED, -1.0, 1.0))
        },
        {
            motor.set(0.0)
        }
    )
        .onlyIf { !beamBreak.get() || !useSensor }
        .until { beamBreak.get() && useSensor }


    fun lower(): Command = startEnd(
        {
            motor.set(-clamp(Constants.Indexer.INDEX_SPEED, -1.0, 1.0))
        },
        {
            motor.set(0.0)
        }
    )

}
