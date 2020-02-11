package smalburg;

public class ParameterNotRightException extends Exception{
	private String grund;
	public ParameterNotRightException(String grund) {
		this.grund = grund;
	}
	public String getGrund() {
		return this.grund;
	}
}
