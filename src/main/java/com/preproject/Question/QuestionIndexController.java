package com.preproject.Question;

import com.preproject.User.entity.User;
import com.preproject.response.MultiResponseDto;
import com.preproject.response.SingleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class QuestionIndexController {

    private final QuestionRepository questionRepository;

    private final QuestionService questionService;

    public QuestionIndexController(QuestionRepository questionRepository, QuestionService questionService) {
        this.questionRepository = questionRepository;
        this.questionService = questionService;
    }

//    @PostMapping("/question/write")
//    public String questionWrite(Question question){
//     questionRepository.save(question);
//     return "redirect:/question";
//    }
@PostConstruct
public void init(){
    questionRepository.save(new Question(10L,"제목1","내용1","작성자1", LocalDateTime.now()));
    questionRepository.save(new Question(11L,"제목2","내용2","작성자2", LocalDateTime.now()));
    questionRepository.save(new Question(12L,"제목3","내용3","작성자3", LocalDateTime.now()));


}

    @PostMapping("/question/write")
    public ResponseEntity questionWrite(Question question){
        questionRepository.save(question);
        return new ResponseEntity<>(question , HttpStatus.CREATED);
    }

    @GetMapping("/question/{question-id}")//다른 회원 정보 조회
    public ResponseEntity getOtherUser(
            @PathVariable("question-id") @Positive long questionId){

        Question question = questionService.findQuestion(questionId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(question)
                , HttpStatus.OK);
    }

    @GetMapping("/question/all") //전체 회원 조회
    public ResponseEntity getUsers(@Positive @RequestParam int page,
                                   @Positive @RequestParam int size) {
        Page<Question> pageQuestions = questionService.findUsers(page - 1, size);
        List<Question> questions = pageQuestions.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(questions,
                        pageQuestions),
                HttpStatus.OK);
    }
}
