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
    object Camera {
        const val CAMERA_HEIGHT_METERS: Double = 0.635
        const val GOAL_HEIGHT_METERS: Double = 2.643
        val CAMERA_PITCH_RADIANS: Double = Units.degreesToRadians(40.0)
    }

    object Controls {
        const val JOYSTICK_LEFT: Int = 0
        const val JOYSTICK_RIGHT: Int = 1
        const val XBOX_CONTROLLER: Int = 2
    }

    object Drivetrain {
        const val MOTOR_RIGHT_1: Int = 1
        const val MOTOR_RIGHT_2: Int = 2
        const val MOTOR_LEFT_1: Int = 3
        const val MOTOR_LEFT_2: Int = 4

        const val SENSOR_UNITS_PER_REV: Double = 2048.0
        val WHEEL_DIAMETER: Double = Units.inchesToMeters(6.0)
        const val GEAR_RATIO: Double = 10.71
        val WHEEL_CIRCUMFERENCE: Double = Math.PI * WHEEL_DIAMETER
        val SENSOR_UNITS_PER_METER: Double = (SENSOR_UNITS_PER_REV * GEAR_RATIO) / WHEEL_CIRCUMFERENCE

        const val DRIVE_VELOCITY_KP: Double = 5.0

        const val TRACK_WIDTH: Double = 19 * 0.0254
        val KINEMATICS: DifferentialDriveKinematics = DifferentialDriveKinematics(TRACK_WIDTH)

        const val RAMSETE_B: Double = 2.0
        const val RAMSETE_ZETA: Double = 0.7

        const val FEED_FORWARD_KS: Double = 0.75495
        const val FEED_FORWARD_KV: Double = 2.3071
        const val FEED_FORWARD_KA: Double = 0.40615
    }

    object Shooter {
        const val BOTTOM: Int = 9
        const val TOP: Int = 5
        const val VELOCITY_TO_RPM: Double = (600 / 2048f).toDouble()

        const val TOP_P: Double = 0.01
        const val TOP_I: Double = 0.00002
        const val TOP_D: Double = 0.001
        const val TOP_F: Double = ((0.5 * 1023) / 11000.0)
        const val BOTTOM_P: Double = 0.04
        const val BOTTOM_I: Double = 0.00001
        const val BOTTOM_D: Double = 0.001
        const val BOTTOM_F: Double = ((0.5 * 1023) / 11000.0)
    }

    object Intake {
        const val MOTOR: Int = 6
        const val ACTUATION_MOTOR: Int = 21

        const val ACTUATION_LIMIT_SWITCH_1: Int = 2
        const val ACTUATION_LIMIT_SWITCH_2: Int = 5
        const val ACTUATION_MOTOR_GEAR_RATIO: Float = 16f * (35f / 22f) * (28f / 19f)
        const val ACTUATION_DEGREES: Double = 56.0
    }

    object Conveyor {
        const val MOTOR: Int = 22
        const val CURRENT_THRESHOLD: Double = 4.4
        const val CURRENT_SMA_PERIOD: Int = 5
        const val BEAM_BREAK: Int = 1
    }

    object Climb {
        const val RIGHT_TELESCOPING_MOTOR: Int = 5
        const val LEFT_TELESCOPING_MOTOR: Int = 6
        const val RIGHT_PIVOT_MOTOR: Int = 10
        const val LEFT_PIVOT_MOTOR: Int = 11

        const val PIVOT_LIMIT_SWITCH_OUT: Int = 3
        const val PIVOT_LIMIT_SWITCH_IN: Int = 4
    }

    object Autonomous {
        const val TURN_KP: Double = 0.25
        const val TURN_KI: Double = 0.0
        const val TURN_KD: Double = 0.015
    }
}