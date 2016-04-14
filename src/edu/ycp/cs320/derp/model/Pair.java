/**
 * File copied from Library example Lab06.
 */

package edu.ycp.cs320.derp.model;

public class Pair<LeftType, RightType> {
	private LeftType left;
	private RightType right;
	
	public Pair(LeftType left, RightType right) {
		this.left = left;
		this.right = right;
	}
	
	public void setLeft(LeftType left) {
		this.left = left;
	}
	
	public LeftType getLeft() {
		return left;
	}
	
	public void setRight(RightType right) {
		this.right = right;
	}
	
	public RightType getRight() {
		return right;
	}
}
