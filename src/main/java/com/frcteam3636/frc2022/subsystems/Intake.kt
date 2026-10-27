//import com.ctre.phoenix6.configs.TalonFXConfiguration
//import com.ctre.phoenix6.hardware.TalonFX
//import com.ctre.phoenix6.signals.InvertedValue
//import com.ctre.phoenix6.signals.NeutralModeValue
//import edu.wpi.first.wpilibj2.command.Command
//import edu.wpi.first.wpilibj2.command.Subsystem
//import frc.Constants
//
//object Intake: Subsystem {
//
//    private var motor = TalonFX(Constants.Intake.MOTOR_CAN_ID).apply {
//        configurator.apply(
//            TalonFXConfiguration().apply {
//                MotorOutput.apply {
//                    NeutralMode = NeutralModeValue.Coast
//                    Inverted = InvertedValue.CounterClockwise_Positive
//                }
//            }
//        )
//    }
//
//     fun intake(): Command = startEnd(
//        {
////            pivotMotor.closedLoopController.setReference(0.5, SparkBase.ControlType.kPosition)
//            motor.set(0.5)
//        },
//        {
////            pivotMotor.closedLoopController.setReference(0.0, SparkBase.ControlType.kPosition)
//            motor.set(0.0)
//        }
//    )
//
//    fun outtake(): Command = startEnd(
//        {
////            pivotMotor.closedLoopController.setReference(0.5, SparkBase.ControlType.kPosition)
//            motor.set(-0.5)
//        },
//        {
////            pivotMotor.closedLoopController.setReference(0.0, SparkBase.ControlType.kPosition)
//            motor.set(0.0)
//        }
//    )
//}