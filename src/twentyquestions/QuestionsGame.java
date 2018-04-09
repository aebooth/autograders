package twentyquestions;

import java.util.Scanner;

/**
 * Created by Alex on 6/12/17.
 */
public class QuestionsGame {
    private Scanner console;
    private QuestionNode tree;

    public QuestionsGame(String object){
        tree = new QuestionNode(object,true);
        console = new Scanner(System.in);
    }

    public QuestionsGame(Scanner save){
        if(save.hasNextLine() && save.nextLine().equals("Q:")){
            tree = new QuestionNode(save.nextLine(),false);
            buildTree(save, tree);
        }else{
            tree = new QuestionNode(save.nextLine(),true);
        }
        console = new Scanner(System.in);
    }

    private void buildTree(Scanner save, QuestionNode current) {
        if (save.hasNextLine()) {
            if(save.nextLine().equals("Q:")) {
                current.yes = new QuestionNode(save.nextLine(), false);
                buildTree(save, current.yes);
            } else {
                current.yes = new QuestionNode(save.nextLine(),true);
            }

            if(save.nextLine().equals("Q:")) {
                current.no = new QuestionNode(save.nextLine(), false);
                buildTree(save, current.no);
            } else {
                current.no = new QuestionNode(save.nextLine(),true);
            }
        }
    }

    public void play(){
    }

    private static class QuestionNode{
        public boolean isAnswer;
        public QuestionNode yes;
        public QuestionNode no;
        public String data;

        public QuestionNode(String data, boolean isAnswer){
            this.data = data;
            this.isAnswer = isAnswer;
            this.yes = null;
            this.no = null;
        }
    }
}
