package com.frcteam3636.frc2022.subsystems.Intake

import edu.wpi.first.wpilibj2.command.Subsystem
import frc.robot.Robot

object Intake : Subsystem{
    private val IO = when(Robot.model) {
        Robot.Model.SIMULATION -> IntakeIOReal()
        Robot.Model.COMPETITION -> IntakeIOReal()
    }

//    private var input =
}
