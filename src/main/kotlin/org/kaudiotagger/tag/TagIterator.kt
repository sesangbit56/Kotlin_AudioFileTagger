import org.kaudiotagger.tag.TagField

//TODO 인터페이스 Tag를 TagIterator로 변경했으니 추후 이걸로 코드를 대폭 수정해야 함
class TagIterator(val it: MutableIterator<Map.Entry<String?, List<TagField>?>>?) : Iterator<TagField> {

    private var fieldsIt : MutableIterator<TagField>? = null

    fun changeIt() {
        if (!it?.hasNext()!!) {
            return
        }

        var e : Map.Entry<String?, List<TagField>?> = it.next()
        var l : MutableList<TagField> = e.value as MutableList<TagField>
        fieldsIt = l.listIterator()
    }

    override fun hasNext() : Boolean{
        if(fieldsIt == null) {
            changeIt()
        }
        return it?.hasNext()!! || (fieldsIt != null && fieldsIt?.hasNext()!!)
    }

    override fun next() : TagField {
        if(fieldsIt?.hasNext() == true) {
            changeIt()
        }

        return fieldsIt?.next()!!
    }

    fun remove() = fieldsIt?.remove()

}