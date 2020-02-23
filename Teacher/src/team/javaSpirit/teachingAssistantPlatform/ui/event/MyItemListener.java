package team.javaSpirit.teachingAssistantPlatform.ui.event;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import team.javaSpirit.teachingAssistantPlatform.common.Communication;
import team.javaSpirit.teachingAssistantPlatform.remoteMonitoring.service.Service;
import team.javaSpirit.teachingAssistantPlatform.studentScore.service.StudentScoreService;
import team.javaSpirit.teachingAssistantPlatform.ui.view.Index;

/**
 * <p>
 * Title: MyItemListener
 * </p>
 * <p>
 * Description: 选择监听事件。根据下拉菜单所选择不同的按钮，完成不同的操作。
 * </p>
 * 
 */
public class MyItemListener implements ItemListener {
	/* 下拉框 */
	private JComboBox<?> comboBox;
	private Index index;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description: 无参构造函数
	 * </p>
	 */
	public MyItemListener() {
		super();
	}

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description: 有参构造函数，初始化对象
	 * </p>
	 * 
	 * @param comboBox
	 */
	public MyItemListener(JComboBox<?> comboBox, Index index) {
		this.comboBox = comboBox;
		this.index = index;
	}

	/**
	 * 选择下拉菜单触发的事件
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String item = (String) comboBox.getSelectedItem();
			String info[] = item.split(" ");
			int id = Integer.valueOf(info[0]);
			// 成绩录入的服务
			StudentScoreService ss = new StudentScoreService();
			index.courseList = ss.courseStudent(id);
			index.contentpl.validate(); 
			index.contentpl.repaint(); 
			index.mode.fireTableDataChanged();
			index.setContentpl();
			index.setGroupLayout();
			index.selectstuScore();
		}
	}

}
