package team.javaSpirit.teachingAssistantPlatform.record.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import team.javaSpirit.teachingAssistantPlatform.entity.Record;
import team.javaSpirit.teachingAssistantPlatform.entity.Score;
import team.javaSpirit.teachingAssistantPlatform.record.dao.RecordDao;
import team.javaSpirit.teachingAssistantPlatform.signIn.dao.StudentCourseDao;

public class RecordService {
	/* Dao类的对象 */
	private static RecordDao recordDao = new RecordDao();
	/**
	 * <p>
	 * Title: getRecord
	 * </p>
	 * <p>
	 * Description: 通过学号，得到签到信息
	 * </p>
	 * 
	 * @param sid 学号
	 */
	public List<Record> getRecord(String sid) {
		List<Record> list = recordDao.allRecordBysid(sid);
		List<Record> sublist = new ArrayList<Record>();
		Date current = new Date();
		for (Record re:list) {
			long day = (current.getTime()-re.getDate().getTime())/(1000*60*60*24);
			if ( day< 7) 
				sublist.add(re);
		}
		return sublist;
	}
	
	/**
	 * <p>
	 * Title: getScore
	 * </p>
	 * <p>
	 * Description: 通过学号，得到签到信息
	 * </p>
	 * 
	 * @param sid 学号
	 */
	public List<Score> getScore(String sid) {
		List<Score> list = recordDao.allScoreBysid(sid);
		return list;
	}
}
