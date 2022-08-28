package com.preproject.Question;

import com.preproject.User.entity.User;
import com.preproject.exception.BusinessLogicException;
import com.preproject.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public Question findQuestion(long questionId) {
        return  findVerifiedQuestion(questionId);
    }

    public Page<Question> findUsers(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("questionId").descending()));
    }
    public Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalMember =
                questionRepository.findById(questionId);
        Question findQuestion =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return findQuestion;
    }
}
