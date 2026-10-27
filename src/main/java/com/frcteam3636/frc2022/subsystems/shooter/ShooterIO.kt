package com.frcteam3636.frc2022.subsystems.shooter

import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.hardware.TalonFX
import com.ctre.phoenix6.signals.InvertedValue
import com.ctre.phoenix6.signals.NeutralModeValue
import com.revrobotics.spark.SparkBase.PersistMode
import com.revrobotics.spark.SparkBase.ResetMode
import com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless
import com.revrobotics.spark.SparkMax
import com.revrobotics.spark.config.SparkBaseConfig
import com.revrobotics.spark.config.SparkMaxConfig
import com.frcteam3636.frc2022.CTREDeviceId
import com.frcteam3636.frc2022.utils.math.amps
import com.frcteam3636.frc2022.utils.math.inVolts
import com.frcteam3636.frc2022.utils.math.rotationsPerSecond
import com.frcteam3636.frc2022.utils.math.rpm
import com.frcteam3636.frc2022.utils.math.volts
import org.team9432.annotation.Logged
import edu.wpi.first.units.measure.Voltage
import edu.wpi.first.wpilibj.DigitalInput

@Logged
open class ShooterInputs{
    var topMotorVelocity = 0.0.rpm
    var topMotorCurrent = 0.0.amps
    var topMotorVoltage = 0.0.volts

    var BottomMotorVelocity = 0.0.rpm
    var BottomMotorCurrent = 0.0.amps
    var BottomMotorVoltage = 0.0.volts
}

interface ShooterIO{
    fun updateInputs(inputs: ShooterInputs)
    fun setTopMotorSpeed(percentage: Double)
    fun setBottomMotorSpeed(percentage: Double)

    fun setTopMotorVoltage(volts: Voltage)
    fun setBottomMotorVoltage(volts: Voltage)
}

class ShooterIOReal : ShooterIO{
    private var topMotor = TalonFX(CTREDeviceId.ShooterTopMotor.ID).apply {
        configurator.apply(
            TalonFXConfiguration().apply {
                MotorOutput.apply {
                    NeutralMode = NeutralModeValue.Coast
                    Inverted = InvertedValue.CounterClockwise_Positive
                }
            }
        )
    }

    private var bottomMotor = TalonFX(CTREDeviceId.ShooterBottomMotor.ID).apply {
        configurator.apply(
            TalonFXConfiguration().apply {
                MotorOutput.apply {
                    NeutralMode = NeutralModeValue.Coast
                    Inverted = InvertedValue.Clockwise_Positive
                }
            }
        )
    }

    override fun updateInputs(inputs: ShooterInputs) {
        inputs.topMotorCurrent = topMotor.get().amps
        inputs.topMotorVoltage = topMotor.get().volts
        inputs.topMotorVelocity = topMotor.get().rpm

        inputs.BottomMotorCurrent = bottomMotor.get().amps
        inputs.BottomMotorVoltage = bottomMotor.get().volts
        inputs.BottomMotorVelocity = bottomMotor.get().rpm
    }

    override fun setTopMotorSpeed(percentage: Double) {
        topMotor.set(percentage)
    }

    override fun setBottomMotorSpeed(percentage: Double) {
        bottomMotor.set(percentage)
    }

    override fun setTopMotorVoltage(volts: Voltage) {
        topMotor.setVoltage(volts.inVolts())
    }

    override fun setBottomMotorVoltage(volts: Voltage) {
        bottomMotor.setVoltage(volts.inVolts())
    }

    companion object Constants {

    }
}


