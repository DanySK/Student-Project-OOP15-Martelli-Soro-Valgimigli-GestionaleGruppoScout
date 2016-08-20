package view.unit_manager.utility;

import java.time.LocalDate;

public interface UnitLeaderJPanel {

	/**
	 * return name JtextFiled's Text
	 * @return
	 */
	String getNome();

	/**
	 * return surname JtextFiled's Text
	 * @return
	 */

	String getSurname();

	/**
	 * Return a localDate generated on user input
	 * @return
	 */

	LocalDate getDate();

	/**
	 * return phone JtextFiled's Text
	 * @return
	 */
	String getPhone();

	/**
	 * add a Sex Chooser in the JDialog
	 */
	void addSexChoose();

	/**
	 * return choosen sec(true is male)
	 * @return
	 */
	boolean isSex();

}