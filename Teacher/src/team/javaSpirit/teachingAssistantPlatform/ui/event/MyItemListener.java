package team.javaSpirit.teachingAssistantPlatform.ui.event;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import team.javaSpirit.teachingAssistantPlatform.common.Communication;
import team.javaSpirit.teachingAssistantPlatform.remoteMonitoring.service.Service;

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
	/* 开启连接的服务 */
	private Service service;
	/* 下拉框 */
	private JComboBox<?> comboBox;

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
	public MyItemListener(JComboBox<?> comboBox,Service service) {
		this.service = service;
		this.comboBox = comboBox;
	}

	/**
	 * 选择下拉菜单触发的事件
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (comboBox.getSelectedItem() == "开启") {
				// 开启服务
				service.openService(Communication.tPort);
			} else if (comboBox.getSelectedItem() == "关闭") {
				// 关闭服务
				service.closeServise();
			} else if (comboBox.getSelectedItem() == "开启共享") {
				// 开启屏幕共享
				service.openScreenShare();
			} else {
				// 关闭屏幕共享
				service.closeScreenShare();
			}
		}
	}

}