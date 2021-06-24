package app.views.env.entities;

import app.views.env.renderEngine.DisplayManager;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	private float distanceFromAirplane = 50;
	private float angleAroundAirplane = 0;

	private final Vector3f position = new Vector3f(0,5,0);
	private Airplane airplane;
	private float pitch = 10;
	private float yaw = 0;
	private float roll = 0;
	
	public Camera(Airplane airplane){
		this.airplane = airplane;
	}
	
	public void move(){
		calculateZoom();
		calculatePitch();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (airplane.getRotY() + angleAroundAirplane);
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

	private void calculateCameraPosition(float horizontalDistance, float verticalDistance) {
		float theta = airplane.getRotY() + angleAroundAirplane;
		float offsetX = (float) (horizontalDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizontalDistance * Math.cos(Math.toRadians(theta)));
		position.x = airplane.getPosition().x - offsetX;
		position.z = airplane.getPosition().z - offsetZ;
		position.y = airplane.getPosition().y + verticalDistance;
	}

	private float calculateHorizontalDistance() {
		return (float) (distanceFromAirplane * Math.cos(Math.toRadians(pitch)));
	}

	private float calculateVerticalDistance() {
		return (float) (distanceFromAirplane * Math.sin(Math.toRadians(pitch)));
	}

	private void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * DisplayManager.getFrameTimeSeconds();
		distanceFromAirplane -= zoomLevel;
	}

	private void calculatePitch() {
		if(Mouse.isButtonDown(0)) {
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
			float angleChange = Mouse.getDX() * 0.3f;
			angleAroundAirplane -= angleChange;
		}
	}
}
