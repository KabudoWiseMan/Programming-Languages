/**
 * Created by vsevolodmolchanov on 26.03.17.
 */
public class Element<T>
{
    private T x;
    private Element<T> parent;
    private int depth;

    // Создаёт новый элемент со значением x
    public Element(T x)
    {
        this.x = x;
        parent = this;
        depth = 0;
    }

    // Возвращает значение элемента
    public T x()
    {
        return x;
    }

    public Element<T> find(Element<T> x) {
        if(x == x.parent) {
            return x;
        } else {
            return x.parent = find(x.parent);
        }
    }

    // Определяет, принадлежит ли текущий элемент
    // тому же множеству, что и элемент elem
    public boolean equivalent(Element<T> elem)
    {
        return find(this) == find(elem);
    }

    // Объединяет множество, которому принадлежит текущий
    // элемент, с множеством, которому принадлежит
    // элемент elem
    public void union(Element<T> elem)
    {
        Element<T> a = find(this);
        Element<T> b = find(elem);
        if(a.depth < b.depth) {
            a.parent = b;
        } else {
            b.parent = a;
            if(a.depth == b.depth && a != b) {
                a.depth++;
            }
        }
    }
}
