/* (C)2022 Max Niederman, Silas Gagnon, and contributors */
package frc.robot

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics
import edu.wpi.first.math.util.Units

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 *
 * It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
class Constants {
    object Drivetrain {
        const val MOTOR_RIGHT_1: Int = 1
        const val MOTOR_RIGHT_2: Int = 2
        const val MOTOR_LEFT_1: Int = 3
        const val MOTOR_LEFT_2: Int = 4
    }
}