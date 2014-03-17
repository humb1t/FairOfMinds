package com.nipu.controller;

import com.google.common.collect.Lists;
import com.nipu.domain.Answer;
import com.nipu.domain.Criteria;
import com.nipu.domain.Role;
import com.nipu.domain.User;
import com.nipu.repository.*;
import com.nipu.service.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class AdminController
{

    @Autowired
    UserRepository userRepository;
    @Autowired
    AchievementRepository achievementRepository;
    @Autowired
    CriteriaRepository criteriaRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/init")
    public void init(final Model model) throws IOException
    {
        User admin = new User("admin", "admin", "admin@mail.ru", "1234");
        Role adminRole = roleRepository.save(new Role(Constants.ADMIN_ROLE));
        Role applRole = roleRepository.save(new Role(Constants.APPLICANT_ROLE));
        admin.addRole(adminRole);
        admin.addRole(applRole);
        userRepository.save(admin);

        fillCriteria("1. Масштаб достижения",
                Lists.newArrayList("Районный", "Городской",
                        "Областной", "Всероссийский"), 0.2422519539);
        fillCriteria("2. Контекст достижения",
                Lists.newArrayList("Не связанный с областью достижения",
                        "Популярное и распространенное мероприятия в области достижения",
                        "Популярное, но весьма редкое мероприятие в области достижения",
                        "Элитарное мероприятие в области достижения"), 0.0236413699);
        fillCriteria("3. Ваша роль",
                Lists.newArrayList("Наблюдатель", "Участник",
                        "Организатор", "Представитель жюри"), 0.1141559363);
        fillCriteria("4. Результаты достижения",
                Lists.newArrayList("Благодарность", "Письменная благодарность",
                        "Вознаграждение, повышение или бонус", "Существенное вознаграждение, повышение"),
                0.1141559363);
        fillCriteria("5. Время затраченное на достижение",
                Lists.newArrayList("Меньше месяца", "От месяца до трех месяцев",
                        "От трех месяцев до года", "Больше года"), 0.0510520867);
        fillCriteria("6. Конкуренция в данной области",
                Lists.newArrayList("До 100 участников", "От 100 до 500 участников",
                        "От 500 до 1000 участников", "Больше 1000 участников"), 0.2422519539);
        fillCriteria("7. Ваша мотивация",
                Lists.newArrayList("Материальная", "Имиджевая",
                        "Индивидуальная", "Общественная"), 0.1141559363);
        fillCriteria("8. Ваша общая занятость",
                Lists.newArrayList("Свободен целыми днями", "Занято 30% от общего времени",
                        "Свободен по выходным", "Очень загруженный график"), 0.0236413699);
        fillCriteria("9. Ваше материальное состояние",
                Lists.newArrayList("Доход меньше 5 т.р.", "Доход от 5 до 15 т.р.",
                        "Доход от 15 до 30 т.р.", "Доход больше 30 т.р."), 0.0236413699);
        fillCriteria("10. Ваше отношение к сфере достижения",
                Lists.newArrayList("Не имею прямого отношения", "Увлекаюсь данным направлением",
                        "Работаю в данной сфере", "Имею ученую степень"), 0.0510520867);
    }

    private void fillCriteria(String title, ArrayList<String> answers, double rate)
    {
        Answer a1 = new Answer(answers.get(0));
        a1 = answerRepository.save(a1);
        Answer a2 = new Answer(answers.get(1));
        a2 = answerRepository.save(a2);
        Answer a3 = new Answer(answers.get(2));
        a3 = answerRepository.save(a3);
        Answer a4 = new Answer(answers.get(3));
        a4 = answerRepository.save(a4);
        Criteria criteria = new Criteria(title,
                Lists.newArrayList(a1, a2, a3, a4), rate);
        criteriaRepository.save(criteria);
    }
}
