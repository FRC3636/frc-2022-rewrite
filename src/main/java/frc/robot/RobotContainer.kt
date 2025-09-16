package frc.robot

import edu.wpi.first.units.Units.*
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.button.CommandJoystick
import edu.wpi.first.wpilibj2.command.button.CommandXboxController

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the [Robot]
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 *
 * In Kotlin, it is recommended that all your Subsystems are Kotlin objects. As such, there
 * can only ever be a single instance. This eliminates the need to create reference variables
 * to the various subsystems in this container to pass into to commands. The commands can just
 * directly reference the (single instance of the) object.
 */
object RobotContainer
{

    private val controller = CommandXboxController(2)
    private val joystickLeft = CommandJoystick(0)
    private val joystickRight = CommandJoystick(1)

    init
    {
        configureBindings()
    }

    /** Use this method to define your `trigger->command` mappings. */
    private fun configureBindings()
    {
        Drivetrain.defaultCommand = Drivetrain.driveWithController(controller.hid)
        Indexer.defaultCommand = Indexer.index(true)

        joystickRight.button(1).whileTrue(
            Indexer.index()
        )

        joystickLeft.button(1).whileTrue(
            Indexer.index()
        )

        controller.leftBumper().whileTrue(Intake.outtake())
        controller.rightBumper().whileTrue(Intake.intake())

        controller.a().whileTrue(
            Shooter.spinUp(1.0),
        )

        controller.b().whileTrue(
            Shooter.spinUp(0.3)
        )

        controller.x().whileTrue(
            Shooter.spinUp(0.5),
        )

        controller.b().whileTrue(
            Drivetrain.alignToTarget()
        )
    }

    fun getAutonomousCommand(): Command?
    {
        // TODO: Implement properly
        return null
    }
}