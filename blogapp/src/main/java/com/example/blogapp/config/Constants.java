package com.example.blogapp.config;

import com.example.blogapp.models.Account;
import com.example.blogapp.models.Perms;
import com.example.blogapp.models.Post;
import com.example.blogapp.tempstorage.PermsRepository;

import com.example.blogapp.actions.AccountCreatorFinder;
import com.example.blogapp.actions.PostUpdator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Constants implements CommandLineRunner {

    @Autowired
    private PostUpdator postUpdator;

    @Autowired
    private AccountCreatorFinder accountCreatorFinder;

    @Autowired
    private PermsRepository permsRepository;

    @Override
    public void run(String... args) {
        List<Post> posts = postUpdator.getAll();

        if (posts.isEmpty()) {

            Perms user = new Perms();
            user.setName("ROLE_USER");
            permsRepository.save(user);

            Perms admin = new Perms();
            admin.setName("ROLE_ADMIN");
            permsRepository.save(admin);

            Account account1 = new Account();

            Account account2 = new Account();

            account1.setFirstName("Deeptarka");
            account1.setLastName("Dey");
            account1.setEmail("deeptarkadey29@gmail.com");
            account1.setPassword("2908");
            Set<Perms> perms1 = new HashSet<>();
            permsRepository.findById("ROLE_ADMIN").ifPresent(perms1::add);
            account1.setPerms(perms1);

            account2.setFirstName("Sumaira");
            account2.setLastName("Shantanu");
            account2.setEmail("sumaira.shantanu@gmail.com");
            account2.setPassword("abcd");
            Set<Perms> perms2 = new HashSet<>();
            permsRepository.findById("ROLE_ADMIN").ifPresent(perms2::add);
            account2.setPerms(perms2);





            accountCreatorFinder.save(account1);

            accountCreatorFinder.save(account2);


            Post post1 = new Post();
            post1.setTitle("Should we abandon conformity altogether?\n");
            post1.setBody("""
                    As humans, we are hardwired for social connection and acceptance. It's the reason why we join social groups, adhere to certain dress codes, and even adopt certain beliefs and values. But how much of our conformity is driven by a need for belonging, and how much is due to a lack of critical thinking? Margaret Drabble's assertion that our desire to conform is greater than our respect for objective facts is a cautionary tale. It warns us that the need for social validation can easily override our ability to question and challenge established norms and beliefs. But does this mean we should abandon conformity altogether? Not necessarily, while this claim is a thought-provoking one, it is not entirely true in all cases.


                    On one hand, there are certainly times when we priorities conformity over truth. For instance, individuals may hesitate to speak up about the unethical behavior of their coworkers or superiors for fear of retaliation or being ostracized. Similarly, groupthink can lead people to accept false information as true without questioning its validity. In these cases, the need for acceptance can outweigh the importance of objective facts. While our desire to conform can be a powerful force, it's important to remember that sightlessly following the crowd can lead us down some dark alleys. It's like blindly following a herd of lemmings off a cliff - you may find yourself hurtling towards certain destruction without any sense of direction or independent thought.


                    However, there are also many instances where individuals put truth and objective facts above conformity. Scientists, for instance, strive to uncover the truth about the natural world and are often willing to challenge established theories and beliefs. Journalists and investigators also work tirelessly to uncover the truth and report it to the public, even in the face of opposition and threats to their safety. Moreover, the pursuit of truth has been the driving force behind many of humanity's greatest achievements and advancements, from scientific discoveries to social reforms.


                    Furthermore, not all conformity is bad. In some cases, following the norms and expectations of a group can be a way of promoting social order and cooperation. For example, adhering to traffic laws and customs when driving in a foreign country can help prevent accidents and misunderstandings. Additionally, conformity played a crucial role in societal movements such as the civil rights movement in the United States. The success of the movement relied heavily on the collective effort of activists and supporters who followed the nonviolent resistance tactics led by Martin Luther King Jr. However, conformity can become problematic when it leads to the perpetuation of harmful beliefs and practices.


                    In conclusion, let's all take a moment to reflect on our own lives and the times we've mindlessly followed the crowd. Maybe it was copying someone else's homework in high school or pretending to like a certain type of music just to fit in with a particular group. We've all been there, and it's natural to want to be accepted by our peers. But it's important to remember that blindly conforming can sometimes lead us down the wrong path. So, let's not be afraid to stand out from the crowd and think for ourselves, or we might end up being the person who brings a 10-pound backpack to school every day, even though we only have two classes. And trust me, nobody wants to be that person.""");
            post1.setAccount(account1);



            postUpdator.save(post1);

        }
    }

}
