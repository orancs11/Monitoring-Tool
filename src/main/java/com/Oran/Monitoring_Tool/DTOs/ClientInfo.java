package com.Oran.Monitoring_Tool.DTOs;

import java.io.OutputStream;
import java.io.Serializable;

public record ClientInfo(String hostName, String OS, Double currCPUConsumption, Double currMemoryConsumption) implements Serializable {}
