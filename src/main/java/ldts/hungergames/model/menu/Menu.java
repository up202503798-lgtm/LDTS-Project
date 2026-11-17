package ldts.hungergames.model.menu;

import java.util.List;

public class Menu {
    private final String title;
    private final List<String> options;
    private int selectedOption=0;
    public Menu(String title){
        this.title = title;
        this.options = List.of("Start","Quit");
    }

    public String getTitle() {
        return title;
    }

    public List<String> getOptions() {
        return options;
    }
    public void nextOption(){
        if(selectedOption<options.size()-1){
            selectedOption++;
        }
    }
    public void prevOption(){
        if(selectedOption>0){
            selectedOption--;
        }
    }
    public boolean isSelected(int index){
        return index==selectedOption;
    }
    public boolean isSelectedStart(){
        return selectedOption==0;
    }


}