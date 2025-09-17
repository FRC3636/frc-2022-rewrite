/* (C)2022 Max Niederman, Silas Gagnon, and contributors */
package frc.robot

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
        const val BASE_VOLTAGE = 12.0
        // Change the ones below me!
        const val DRIVING_SENSITIVITY = 0.5
        const val TURNING_SENSITIVITY = 0.15
    }

    object Indexer {
        const val INDEX_SPEED = 0.75
        const val BEAM_BREAK_PORT = 1
        const val MOTOR_CAN_ID = 22
    }

    object Intake {
        const val MOTOR_CAN_ID = 6
    }

    object Shooter {
        const val TOP_MOTOR_CAN_ID = 5
        const val BOTTOM_MOTOR_CAN_ID = 9
        // Change the ones below me!
        const val TOP_SPIN_UP_BASE_SPEED = 12.0
        const val BOTTOM_SPIN_UP_BASE_SPEED = 10.0
    }
}