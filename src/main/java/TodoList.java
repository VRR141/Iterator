import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TodoList implements Iterable<String> {

    private List<String> primary = new ArrayList<>();
    private List<String> secondary = new ArrayList<>();


    public TodoList addPrimary(String task){
        primary.add(task);
        return this;
    }

    public TodoList addSecondary(String task){
        secondary.add(task);
        return this;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            boolean isPrimary = true;
            int nextItem;

            @Override
            public boolean hasNext() {
                if (isPrimary){
                    if (nextItem >= primary.size()){
                        return !secondary.isEmpty();
                    } else {
                        return true;
                    }
                } else {
                    return nextItem < secondary.size();
                }
            }

            @Override
            public String next() {
                if (isPrimary) {
                    if (nextItem >= primary.size()){
                        isPrimary = false;
                        String ans = secondary.get(0);
                        nextItem = 1;
                        return ans;
                    } else {
                        String ans = primary.get(nextItem);
                        nextItem++;
                        return ans;
                    }
                } else {
                    String ans = secondary.get(nextItem);
                    nextItem++;
                    return ans;
                }
            }
        };
    }

}
