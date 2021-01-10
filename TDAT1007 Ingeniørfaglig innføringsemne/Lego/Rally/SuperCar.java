import lejos.hardware.motor.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.sensor.EV3ColorSensor;

class SuperCar{
	private static boolean turning;

	public static void main(String[] args){

		Brick brick = BrickFinder.getDefault();
		Port s1 = brick.getPort("S1");
		Port s2 = brick.getPort("S2");

		EV3 ev3 = (EV3) BrickFinder.getLocal();

		TextLCD lcd = ev3.getTextLCD();
		Keys keys = ev3.getKeys();

		EV3ColorSensor sensor1 = new EV3ColorSensor(s1);
		SampleProvider sampler1 = sensor1.getMode("RGB");
		NXTLightSensor sensor2 = new NXTLightSensor(s2);
		SampleProvider sampler2 = sensor2.getRedMode();

		float[] samples = new float[sampler1.sampleSize() * 2];

		try{
			turning = false;

			lcd.drawString("Trykk for starte", 0, 1);
			keys.waitForAnyPress();

			Motor.A.setSpeed(350);
			Motor.B.setSpeed(350);
			Motor.A.forward();
			Motor.B.forward();


			while(true){

				sampler1.fetchSample(samples, 0);
				sampler2.fetchSample(samples, 1);

				if (samples[0] < 0.05 && samples[1] < 0.475){
					Motor.A.forward();
					Motor.B.forward();
				}
				else if (samples[0] < 0.05) TurnUntilWhite(true);
				else if (samples[1] < 0.475) TurnUntilWhite(false);
				else if (turning){
					Motor.A.forward();
					Motor.B.forward();
					turning = false;
				}
			}
		}
		catch(Exception e){
			System.out.println("Feil: "+ e +"");
		}
	}
	static void TurnUntilWhite(boolean rotateLeft)
	{
		turning = true;
		if (rotateLeft){
			Motor.B.stop(true);
			Motor.A.backward();
			try{
				Thread.sleep(500);
			}
			catch(Exception e ){}
		}
		else {
			Motor.A.stop(true);
			Motor.B.backward();
		}
	}
}