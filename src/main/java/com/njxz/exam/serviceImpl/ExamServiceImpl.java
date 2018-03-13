package com.njxz.exam.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.aspectj.util.LangUtil.ProcessController.Thrown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.njxz.exam.dao.ExamMapper;
import com.njxz.exam.modle.BasicException;
import com.njxz.exam.modle.Exam;
import com.njxz.exam.modle.ExamExample;
import com.njxz.exam.modle.ExamExample.Criteria;
import com.njxz.exam.modle.ExamQuestions;
import com.njxz.exam.modle.ExamQuestiontype;
import com.njxz.exam.modle.Subject;
import com.njxz.exam.modle.User;
import com.njxz.exam.modle.UserSubject;
import com.njxz.exam.service.ExamQuestionsService;
import com.njxz.exam.service.ExamQuestiontypeService;
import com.njxz.exam.service.ExamService;
import com.njxz.exam.service.SubjectService;
import com.njxz.exam.service.UserSubjectService;
import com.njxz.exam.util.StringUtil;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

@Service("examService")
public class ExamServiceImpl implements ExamService{
	
	@Autowired
	ExamMapper examMapper;
	
	@Autowired
	ExamQuestionsService eqService;
	
	@Autowired
	ExamQuestiontypeService eqtService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	UserSubjectService userSubjectService;
	
	@Override
	public int add(Exam exam) {
		return examMapper.insert(exam);
	}

	@Override
	@Transactional
	//试卷信息存入数据库---exam,exam_questions,exam_questiontype
	public boolean inToDB(Long eId,Long uId, String paperName,int paperTotalScore, double paperDifficutty, Byte eStatus,
			Long sId, List<Long> qIdList, List<Map<String, Object>> qtList) throws BasicException{
		int count1;
		ExamQuestions tempExamQuestions;
		List<ExamQuestions> eqList=new ArrayList<>();
		List<ExamQuestiontype> eqtList=new ArrayList<>();
		ExamQuestiontype tempExamQuestiontype;
		Long qtId;Short questionNum;Short typeScore;Byte typeSort;
		//新建试卷
		Exam exam=new Exam(eId,sId,uId, new Date(), paperName, paperDifficutty);
		int count= add(exam);
		
		if(count!=1) {
			throw new BasicException("exam添加失败");
		}
		
		//存入examQuestions
		for(Long qId:qIdList) {
			tempExamQuestions=new ExamQuestions(exam.geteId(),eStatus,qId);
			eqList.add(tempExamQuestions);
		}
		
		count1=eqService.add(eqList);
		if(count1<0) {
			throw new BasicException("examQuestions添加失败");
		}
		//存入examQuestiontype
		for(Map<String, Object> qtMap:qtList) {
			qtId=Long.parseLong(qtMap.get("qtId").toString());
			questionNum=Short.parseShort(qtMap.get("qtNum").toString());
			typeScore=Short.parseShort(qtMap.get("qtScore").toString());
			typeSort=Byte.parseByte(qtMap.get("qtOrder").toString());
			tempExamQuestiontype=new ExamQuestiontype(exam.geteId(),qtId,questionNum,typeScore,typeSort);
			eqtList.add(tempExamQuestiontype);
		}
		
		count1=eqtService.add(eqtList);
		if(count1<0) {
			throw new BasicException("examQuestions添加失败");
		}
		return true;
	}

	//根据用户Id抽取试卷
	public List<Exam> getExamByUserId(User user,int pageNum,int pageSize) {
		List<UserSubject> listUS = null;
		List<Subject> listS=new ArrayList<>();
		List<Long> sIdList = new ArrayList<Long>();//所有科目Id
		if(user==null) {
			return null;
		}
		
		if(user.getPower()==3) {
			listS=subjectService.selectAllSubject();
		}else {
			//非管理员，查user-subject表
			listUS = userSubjectService.getSubjects(user.getuId().toString());
			// 根据得到的listUS，将所有科目表查出来
			for (UserSubject us : listUS) {
				Subject subject = subjectService.getSubjectById(us.getSubjectId());
				listS.add(subject);
			}
		}
		for(Subject subject:listS) {
			sIdList.add(subject.getsId());
		}
		ExamExample examExample=new ExamExample();
		Criteria criteria=examExample.createCriteria();
		criteria.andSubjectIdIn(sIdList);
		
		PageHelper.startPage(pageNum, pageSize);
		return examMapper.selectByExample(examExample);
	}

	@Override
	public Exam get(Long eId) {
		// TODO Auto-generated method stub
		return examMapper.selectByPrimaryKey(eId);
	}

	@Override
	public boolean isHasExamType(byte examType) {
		int count=examMapper.quesNumOfExamType(examType);
		if(count>0) {
			return true;
		}
		return false;
	}

	@Override
	public int updateByPrimaryKey(Exam exam) {
		return examMapper.updateByPrimaryKey(exam);
	}

	//删除试卷信息--exam,exam_questiosns,exam_questiontype
	@Override
	@Transactional
	public boolean deleteExamByExamId(Long examId) {
		try {
			//删除试卷题型信息
			if(eqtService.deleteExamQuestiontypesByEId(examId)<1) {
				throw new BasicException("根据试卷id删除试卷题型信息失败");
			}
			//删除试卷题目信息
			if(eqService.deleteExamQuestionsByEId(examId)<1) {
				throw new BasicException("根据试卷id删除试卷题目失败");
			}
			//删除试卷实体
			if(examMapper.deleteByPrimaryKey(examId)!=1) {
				throw new BasicException("根据试卷id删除试卷");
			}
			
		}catch (Exception e) {
			throw new BasicException("删除试卷信息异常");
		}
		
		return true;
	}


	
}
