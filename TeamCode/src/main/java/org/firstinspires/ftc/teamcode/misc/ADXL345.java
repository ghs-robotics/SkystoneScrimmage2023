package org.firstinspires.ftc.teamcode.misc;

import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchDevice;
import com.qualcomm.robotcore.hardware.configuration.I2cSensor;
import com.qualcomm.robotcore.util.TypeConversion;

@I2cSensor(name = "ADXL345 Accelerometer", description = "Acceleration Sensor from Adafruit", xmlTag = "ADXL345")
public class ADXL345 extends I2cDeviceSynchDevice<I2cDeviceSynch> {

    // this sensor (probably) uses address 0x53 followed by a R/W bit
    private final I2cAddr ADDRESS_I2C_DEFAULT_READ = new I2cAddr(0x53);
//    private final I2cAddr ADDRESS_I2C_DEFAULT_WRITE = new I2cAddr(0xA6);

    @Override
    public Manufacturer getManufacturer() {
        return Manufacturer.Adafruit;
    }

    @Override
    protected synchronized boolean doInitialize() {
        return true;
    }

    @Override
    public String getDeviceName() {
        return "Adafruit ADXL345 Digital Accelerometer";
    }

    public byte getDeviceIDRaw() {
        return readByte(Register.DEVID);
    }

    protected void writeShort(final Register reg, short value) {
        deviceClient.write(reg.bVal, TypeConversion.shortToByteArray(value));
    }

    protected short readShort(Register reg) {
        return TypeConversion.byteArrayToShort(deviceClient.read(reg.bVal, 2));
    }

    protected void writeByte(final Register reg, byte value) {
        deviceClient.write(reg.bVal, new byte[] {value});
    }

    protected byte readByte(Register reg) {
        return deviceClient.read(reg.bVal,1)[0];
    }

    public ADXL345(I2cDeviceSynch deviceClient) {
        super(deviceClient, true);

        this.deviceClient.setI2cAddress(ADDRESS_I2C_DEFAULT_READ);

        super.registerArmingStateCallback(false);
        this.deviceClient.engage();
    }

    protected void setOptimalReadWindow() {
        I2cDeviceSynch.ReadWindow rw = new I2cDeviceSynch.ReadWindow(
                Register.DEVID.bVal,
                Register.LAST.bVal - Register.DEVID.bVal,
                I2cDeviceSynch.ReadMode.REPEAT
        );
        this.deviceClient.setReadWindow(rw);
    }

    public enum Register {
        DEVID(0x00),
        X_OFFSET(0x1E),
        Y_OFFSET(0x1F),
        Z_OFFSET(0x20),
        POWER_SAVE_CONTROL(0x2D),
        DATA_FORMAT_CONTROL(0x31),
        X_DATA_0(0x32),
        X_DATA_1(0x33),
        Y_DATA_0(0x34),
        Y_DATA_1(0x35),
        Z_DATA_0(0x36),
        Z_DATA_1(0x37),
        LAST(Z_DATA_1.bVal);

        public int bVal;

        Register(int bVal) {
            this.bVal = bVal;
        }
    }
}
