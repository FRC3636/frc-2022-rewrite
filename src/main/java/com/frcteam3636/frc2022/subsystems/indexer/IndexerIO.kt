package com.frcteam3636.frc2022.subsystems.indexer

import com.revrobotics.spark.SparkBase.PersistMode
import com.revrobotics.spark.SparkBase.ResetMode
import com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless
import com.revrobotics.spark.SparkMax
import com.revrobotics.spark.config.SparkBaseConfig
import com.revrobotics.spark.config.SparkMaxConfig
import com.frcteam3636.frc2022.CTREDeviceId
import com.frcteam3636.frc2022.Constants
import com.frcteam3636.frc2022.utils.math.amps
import com.frcteam3636.frc2022.utils.math.rotationsPerSecond
import com.frcteam3636.frc2022.utils.math.volts
import org.team9432.annotation.Logged
import edu.wpi.first.units.measure.Voltage
import edu.wpi.first.wpilibj.DigitalInput

@Logged
open class IndexerInputs{
    var intakeVelocity = 0.rotationsPerSecond
    var intakeVoltage = 0.volts
    var intakeCurrent = 0.amps
}

interface IndexerIO{
    fun setIndexerSpeed(percentage: Double)
    fun setIndexerVoltage(volts: Voltage)
    fun updateInputs(inputs: IndexerInputs)
}

class IndexerIOReal : IndexerIO{
    private var motor = SparkMax(CTREDeviceId.IndexerMotor.ID, kBrushless).apply {
        configure(SparkMaxConfig().apply {
            idleMode(SparkBaseConfig.IdleMode.kBrake)
        }, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters)
    }

    private var beamBreak = DigitalInput(BEAM_BREAK_PORT)

    val beamBroken: Boolean
        get() {
            return !beamBreak.get()
        }

    override fun setIndexerSpeed(percentage: Double) {
        motor.set(percentage)
    }

    override fun setIndexerVoltage(volts: Voltage) {
        motor.setVoltage(volts)
    }

    override fun updateInputs(inputs: IndexerInputs) {
        inputs.intakeVelocity = motor.get().rotationsPerSecond
        inputs.intakeCurrent = motor.get().amps
        inputs.intakeVoltage = motor.get().volts

    }
    companion object Constants {
         val BEAM_BREAK_PORT = 1

    }
}


