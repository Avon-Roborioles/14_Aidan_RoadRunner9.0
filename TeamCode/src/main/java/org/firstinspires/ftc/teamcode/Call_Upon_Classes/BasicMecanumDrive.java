package org.firstinspires.ftc.teamcode.Call_Upon_Classes;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BasicMecanumDrive {
    private double ly;
    private double lx;
    private double rx;
    private DcMotor fl = null;
    private DcMotor bl = null;
    private DcMotor fr = null;
    private DcMotor br = null;


    public void init_drive_motors(HardwareMap hardwareMap) {
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl.setDirection(DcMotor.Direction.REVERSE);
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    public void run_drive_motors(Gamepad gamepad1, Telemetry telemetry){
        ly=gamepad1.left_stick_y*0.8;
        lx=gamepad1.left_stick_x*0.8;
        rx=gamepad1.right_stick_x*0.8;

        if (Math.abs(lx)>Math.abs(ly)) {
            fl.setPower(lx+rx);
            fr.setPower(-lx-rx);
            br.setPower(lx-rx);
            bl.setPower(-lx+rx);

        }else{
            fl.setPower(-ly+rx);
            fr.setPower(-ly-rx);
            bl.setPower(-ly+rx);
            br.setPower(-ly-rx);
        }

        getTelemetry(telemetry);
    }


    public void getTelemetry (Telemetry telemetry){
        telemetry.addData("fl power: ",fl.getPower());
        telemetry.addData("fr power: ",fr.getPower());
        telemetry.addData("bl power: ",bl.getPower());
        telemetry.addData("br power: ",br.getPower());
    }

}
