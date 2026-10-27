package com.frcteam3636.frc2022

import com.ctre.phoenix6.CANBus
import com.ctre.phoenix6.hardware.CANcoder
import com.ctre.phoenix6.hardware.Pigeon2
import com.ctre.phoenix6.hardware.TalonFX

private val canivoreBus = CANBus("*")

enum class CTREDeviceId(val ID: Int, val bus: CANBus) {
    MOTOR_RIGHT_1(1,canivoreBus),
    MOTOR_RIGHT_2(2,canivoreBus),
    MOTOR_LEFT_1(3,canivoreBus),
    MOTOR_LEFT_2(4,canivoreBus),
    IntakeMotor(6, canivoreBus),

    ShooterTopMotor(5, canivoreBus),
    ShooterBottomMotor(9, canivoreBus),

    IndexerMotor(22, canivoreBus),
}

fun CANcoder(id: CTREDeviceId) = CANcoder(id.ID, id.bus)
fun TalonFX(id: CTREDeviceId) = TalonFX(id.ID, id.bus)
fun Pigeon2(id: CTREDeviceId) = Pigeon2(id.ID, id.bus)
