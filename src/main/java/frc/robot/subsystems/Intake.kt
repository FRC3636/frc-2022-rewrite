import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.hardware.TalonFX
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import com.revrobotics.spark.SparkBase
import com.revrobotics.spark.SparkBase.PersistMode
import com.revrobotics.spark.SparkBase.ResetMode
import com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless
import com.revrobotics.spark.SparkMax
import com.revrobotics.spark.config.ClosedLoopConfig
import com.revrobotics.spark.config.SparkBaseConfig
import com.revrobotics.spark.config.SparkMaxConfig
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem
import kotlin.math.PI

object Intake: Subsystem {

    private var motor = TalonFX(6).apply {
        configurator.apply(
            TalonFXConfiguration().apply {
                MotorOutput.apply {
                    NeutralMode = NeutralModeValue.Coast
                    Inverted = InvertedValue.Clockwise_Positive
                }
            }
        )
    }

    private var pivotMotor = SparkMax(0, kBrushless).apply {
        configure(SparkMaxConfig().apply {
            idleMode(SparkBaseConfig.IdleMode.kBrake)

            absoluteEncoder.apply {
                positionConversionFactor(PI * 2)
                velocityConversionFactor((PI * 2) / 60)
            }

            closedLoop.apply {
                pid(0.0, 0.0, 0.0)
                feedbackSensor(ClosedLoopConfig.FeedbackSensor.kAbsoluteEncoder)
                positionWrappingEnabled(true)
                positionWrappingMinInput(0.0)
                positionWrappingMaxInput(PI * 2)
            }

        }, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters)
    }

    override fun periodic() {}

     fun intake(): Command = startEnd(
        {
            pivotMotor.closedLoopController.setReference(0.5, SparkBase.ControlType.kPosition)
            motor.set(0.25)
        },
        {
            pivotMotor.closedLoopController.setReference(0.0, SparkBase.ControlType.kPosition)
            motor.set(0.0)
        }
    )

    fun outtake(): Command = startEnd(
        {
            pivotMotor.closedLoopController.setReference(0.5, SparkBase.ControlType.kPosition)
            motor.set(-0.25)
        },
        {
            pivotMotor.closedLoopController.setReference(0.0, SparkBase.ControlType.kPosition)
            motor.set(0.0)
        }
    )
}