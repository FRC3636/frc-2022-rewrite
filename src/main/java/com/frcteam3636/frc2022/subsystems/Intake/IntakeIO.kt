package com.frcteam3636.frc2022.subsystems.Intake

import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.hardware.TalonFX
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import com.frcteam3636.frc2022.CTREDeviceId
import com.frcteam3636.frc2022.utils.math.amps
import com.frcteam3636.frc2022.utils.math.inVolts
import com.frcteam3636.frc2022.utils.math.rotationsPerSecond
import com.frcteam3636.frc2022.utils.math.volts
import org.team9432.annotation.Logged
import edu.wpi.first.units.measure.Voltage

@Logged
open class IntakeInputs{
    var intakeVelocity = 0.rotationsPerSecond
    var intakeVoltage = 0.volts
    var intakeCurrent = 0.amps

    var pivotVoltage = 0.volts
    var pivotCurrent = 0.amps
}

interface IntakeIO{
    fun setIntakeSpeed(percentage: Double)
    fun setIntakeVoltage(volts: Voltage)
    fun setPivotVoltage(volts: Voltage)
    fun updateInputs(inputs: IntakeInputs)
    fun zeroEncoder()
}

class IntakeIOReal : IntakeIO{
    private var intakeMotor = TalonFX(CTREDeviceId.IntakeMotor.ID).apply {
        configurator.apply(
            TalonFXConfiguration().apply {
                MotorOutput.apply {
                    NeutralMode = NeutralModeValue.Coast
                    Inverted = InvertedValue.CounterClockwise_Positive
                }
            }
        )
    }

    override fun setIntakeSpeed(percentage: Double) {
        intakeMotor.set(percentage)
    }

    override fun setIntakeVoltage(volts: Voltage) {
        intakeMotor.setVoltage(volts.inVolts())
    }

    override fun setPivotVoltage(volts: Voltage) {
        TODO("Not yet implemented")
    }

    override fun updateInputs(inputs: IntakeInputs) {
        inputs.intakeVelocity = intakeMotor.velocity.value
        inputs.intakeCurrent = intakeMotor.supplyCurrent.value
        inputs.intakeVoltage = intakeMotor.supplyVoltage.value

    }

    override fun zeroEncoder() {
        TODO("Not yet implemented")
    }


    companion object Constants {

    }
}


