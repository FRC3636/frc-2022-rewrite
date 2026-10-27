package com.frcteam3636.frc2022.subsystems.drivetrain

import edu.wpi.first.units.measure.AngularVelocity
import edu.wpi.first.units.measure.LinearVelocity
import edu.wpi.first.units.measure.Voltage
import org.team9432.annotation.Logged

@Logged
open class DrivetrainInputs{
    //log every single class

}

interface DrivetrainIO{
    fun updateInputs(inputs: DrivetrainInputs)

}

class DrivetrainIOReal : DrivetrainIO{
    override fun updateInputs(inputs: DrivetrainInputs) {
        TODO("Not yet implemented")
    }

    companion object Constants {

    }
}




