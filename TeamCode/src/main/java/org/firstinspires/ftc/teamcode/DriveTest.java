package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Drive Test", group="Test")
public class DriveTest extends OpMode {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private double preciseMoveSpeed =0.5;
    private double moveSpeed = 21;
    private double turnSpeed = .75;

    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        double left_YAxis;
        double left_XAxis;
        double right_YAxis;
        double right_XAxis;
        double left_trigger;
        double right_trigger;

        left_YAxis = -gamepad2.left_stick_y;
        left_XAxis = gamepad2.left_stick_x;
        right_YAxis = -gamepad2.right_stick_y;
        right_XAxis = gamepad2.right_stick_x;
        left_trigger = gamepad2.left_trigger;
        right_trigger = gamepad2.right_trigger;

        if (left_trigger > 0) {
            turnLeft(left_trigger);

        } else if (right_trigger > 0) {
            turnRight(right_trigger);

        } else if (right_XAxis > 0.2) {
            strafeRight(right_XAxis * preciseMoveSpeed);

        } else if (right_XAxis < -0.2) {
            strafeLeft(right_XAxis * -preciseMoveSpeed);

        } else if (right_YAxis > 0.2) {
            goForward(right_YAxis * preciseMoveSpeed);

        } else if (right_YAxis < -0.2) {
            goBackward(right_YAxis * preciseMoveSpeed);

        } else if (left_XAxis > 0.4) {
            strafeRight(left_XAxis);

        } else if (left_XAxis < -0.4) {
            strafeLeft(-left_XAxis);

        } else if (left_YAxis > 0.2) {
            goForward(left_YAxis);

        } else if (left_YAxis < -0.2) {
            goBackward(left_YAxis);
        } else {
            goStop();
        }
    }

    private void goStop() {
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }

    private void goForward(double speed) {
        leftFront.setPower(speed* moveSpeed);
        leftBack.setPower(speed* moveSpeed);
        rightFront.setPower(speed* moveSpeed);
        rightBack.setPower(speed* moveSpeed);
    }

    private void goBackward(double speed) {
        leftFront.setPower(speed * moveSpeed);
        leftBack.setPower(speed * moveSpeed);
        rightFront.setPower(speed * moveSpeed);
        rightBack.setPower(speed * moveSpeed);
    }

    private void strafeLeft(double speed) {
        leftFront.setPower(speed * -moveSpeed);
        leftBack.setPower(speed * moveSpeed);
        rightFront.setPower(speed * moveSpeed);
        rightBack.setPower(speed * -moveSpeed);
    }

    private void strafeRight(double speed) {
        leftFront.setPower(speed * moveSpeed);
        leftBack.setPower(speed * -moveSpeed);
        rightFront.setPower(speed * -moveSpeed);
        rightBack.setPower(speed * moveSpeed);
    }

    private void turnLeft(double speed) {
        leftFront.setPower(speed * -turnSpeed);
        leftBack.setPower(speed * -turnSpeed);
        rightFront.setPower(speed * turnSpeed);
        rightBack.setPower(speed * turnSpeed);
    }

    private void turnRight(double speed) {
        leftFront.setPower(speed * turnSpeed);
        leftBack.setPower(speed * turnSpeed);
        rightFront.setPower(speed * -turnSpeed);
        rightBack.setPower(speed * -turnSpeed);
    }
}
