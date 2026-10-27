package com.frcteam3636.frc2022.subsystems.indexer

import com.frcteam3636.frc2022.robot.Robot
import com.frcteam3636.frc2022.utils.math.volts
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.Subsystem

object Indexer : Subsystem {
    private val io = when(Robot.model) {
        Robot.Model.SIMULATION -> IndexerIOReal()
        Robot.Model.COMPETITION -> IndexerIOReal()
    }

    var inputs = LoggedIndexerInputs();

    override fun periodic() {
        io.updateInputs(inputs)
    }

    fun index(useBeamBreak: Boolean = false) : Command = Commands.startEnd(
    {
            io.setIndexerVoltage(5.0.volts)
    },
    {
            io.setIndexerVoltage(0.0.volts)
    })
        .onlyIf { io.beamBroken || !useBeamBreak}
        .until { !io.beamBroken && useBeamBreak }
}