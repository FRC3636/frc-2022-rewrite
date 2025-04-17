object Intake: Subsystem {

    open class IntakeInputs {
        var rollerVelocity = 0.rotationsPerSecond
        var rollerCurrent = 0.amps
    }

    private var motor = TalonFX(CTREDeviceId.IntakeMotor).apply {
        configurator.apply(
            TalonFXConfiguration().apply {
                MotorOutput.apply {
                    NeutralMode = NeutralModeValue.Coast
                    Inverted = InvertedValue.Clockwise_Positive
                }

                CurrentLimits.apply {
                    SupplyCurrentLimit = MOTOR_CURRENT_LIMIT.inAmps()
                    SupplyCurrentLimitEnable = true
                }
            }
        )
    }

    fun updateInputs(inputs: IntakeInputs) {
        inputs.rollerVelocity = motor.velocity.value
        inputs.rollerCurrent = motor.supplyCurrent.value
    }

    var inputs = IntakeInputs()

    override fun periodic() {
        updateInputs(inputs)
        Logger.processInputs("Intake", inputs)

    }

    fun intake(): Command = startEnd(
        {
            motor.set(0.25)
        },
        {
            io.setSpeed(0.0)
        }
    )

    fun outtake(): Command = startEnd(
        {
            io.setSpeed(-0.25)
        },
        {
            io.setSpeed(0.0)
        }
    )

    internal companion object Constants {
        private val MOTOR_CURRENT_LIMIT = 35.amps
    }
}