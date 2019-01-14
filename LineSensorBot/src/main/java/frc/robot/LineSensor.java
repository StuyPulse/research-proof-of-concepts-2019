/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SensorUtil;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.hal.DIOJNI;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Add your docs here.
 */
public class LineSensor extends DigitalInput{
    private final int m_channel;
    private int m_handle;
  
    /**
     * Create an instance of a Line Sensor class. Creates a digital input given a channel.
     *
     * @param channel the DIO channel for the digital input 0-9 are on-board, 10-25 are on the MXP
     */
    public LineSensor(int channel) {
      super(channel);
      SensorUtil.checkDigitalChannel(channel);
      m_channel = channel;
  
      m_handle = DIOJNI.initializeDIOPort(HAL.getPort((byte) channel), true);
  
      HAL.report(tResourceType.kResourceType_DigitalInput, channel);
      setName("DigitalInput", channel);
    }
  
    @Override
    public void close() {
      super.close();
      if (m_interrupt != 0) {
        cancelInterrupts();
      }
      DIOJNI.freeDIOPort(m_handle);
      m_handle = 0;
    }
  
    /**
     * Get the value from a digital input channel. Retrieve the value of a single digital input
     * channel from the FPGA.
     *
     * @return the status of the digital input
     */
    public boolean get() {
      return DIOJNI.getDIO(m_handle);
    }
  
    /**
     * Get the channel of the digital input.
     *
     * @return The GPIO channel number that this object represents.
     */
    @Override
    public int getChannel() {
      return m_channel;
    }
  
    /**
     * Get the analog trigger type.
     *
     * @return false
     */
    @Override
    public int getAnalogTriggerTypeForRouting() {
      return 0;
    }
  
    /**
     * Is this an analog trigger.
     *
     * @return true if this is an analog trigger
     */
    @Override
    public boolean isAnalogTrigger() {
      return false;
    }
  
    /**
     * Get the HAL Port Handle.
     *
     * @return The HAL Handle to the specified source.
     */
    @Override
    public int getPortHandleForRouting() {
      return m_handle;
    }
  
    @Override
    public void initSendable(SendableBuilder builder) {
      builder.setSmartDashboardType("Digital Input");
      builder.addBooleanProperty("Value", this::get, null);
    }
}
