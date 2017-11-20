package co.kr.sumin.array.support.custom;

import co.kr.sumin.array.support.basic.InnerAction;

public abstract class EqualsAction implements InnerAction{
	private String equalsTarget;
	public String getEqualsTarget() {
		return equalsTarget;
	}
	public void setEqualsTarget(String equalsTarget) {
		this.equalsTarget = equalsTarget;
	}

	@Override
	public abstract void execute(Object t, int idx);
}
