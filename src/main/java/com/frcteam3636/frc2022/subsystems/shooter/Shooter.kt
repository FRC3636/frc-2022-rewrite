package com.frcteam3636.frc2022.subsystems.shooter

import com.frcteam3636.frc2022.robot.Robot
import com.frcteam3636.frc2022.utils.math.volts
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Subsystem

object Shooter : Subsystem {
    private val io = when(Robot.model) {
        Robot.Model.SIMULATION -> ShooterIOReal()
        Robot.Model.COMPETITION -> ShooterIOReal()
    }

    var inputs = LoggedShooterInputs()

    override fun periodic() {
        io.updateInputs(inputs)
    }

    fun shoot(): Command = startEnd(
        {
            io.setTopMotorVoltage(4.0.volts)
            io.setBottomMotorVoltage(3.0.volts)
        },
        {
            io.setTopMotorVoltage(0.0.volts)
            io.setBottomMotorVoltage(0.0.volts)

        }
    )
}