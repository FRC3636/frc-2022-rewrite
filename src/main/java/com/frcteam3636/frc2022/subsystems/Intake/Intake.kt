package com.frcteam3636.frc2022.subsystems.Intake

import com.frcteam3636.frc2022.robot.Robot
import com.frcteam3636.frc2022.utils.math.volts
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.Subsystem
import org.littletonrobotics.junction.Logger

object Intake : Subsystem{
    private val io = when(Robot.model) {
        Robot.Model.SIMULATION -> IntakeIOReal()
        Robot.Model.COMPETITION -> IntakeIOReal()
    }

    private var inputs = LoggedIntakeInputs();

    override fun periodic() {
        io.updateInputs(inputs)
        Logger.recordOutput("Example Log", "info")
    }

    fun intake() : Command = Commands.startEnd(
        {
            //This once when the commands starts
            io.setIntakeVoltage(5.0.volts)
        },
        {
            //this runs once the commands ends
            io.setIntakeVoltage(0.0.volts)
        }
    )
}
