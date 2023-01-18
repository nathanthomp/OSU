import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * This program inputs an XML RSS (version 2.0) feed from a given URL and
 * outputs various elements of the feed to the console.
 *
 * @author Put your name here
 *
 */
public final class RSSProcessing {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSProcessing() {
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of the {@code XMLTree} matching the
     *         given tag or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of the {@code XMLTree} matching the
     *   given tag or -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        int result = -1;
        for (int i = 0; i < xml.numberOfChildren(); i++) {
            if (xml.child(i).label().equals(tag) && xml.child(i).isTag()) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Processes one news item and outputs the title, or the description if the
     * title is not present, and the link (if available) with appropriate
     * labels.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures out.content = #out.content * [the title (or description) and
     *          link]
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        if (item.label().equals("item")) {

            if (getChildElement(item, "title") != -1) { // if item has title

                if (item.child(getChildElement(item, "title")).child(0).label()
                        .equals("")) { // item has title and does not have text child
                    out.println("item has a title tag with no text child");
                } else { // item has title and title has a text child
                    out.println("Title: "
                            + item.child(getChildElement(item, "title"))
                                    .child(0).label());
                }
            } else { // item has no title, item has description

                if (item.child(getChildElement(item, "description")).child(0)
                        .label().equals("")) { // item has description but no text
                    out.println(
                            "item has a description tag with no text child");
                } else { // item has description tag and description has a text child
                    out.println("Description: "
                            + item.child(getChildElement(item, "Description"))
                                    .child(0).label());
                }
            }

            if (getChildElement(item, "link") != -1) { // if item has link
                out.println("Link: " + item.child(getChildElement(item, "link"))
                        .child(0).label());
            }
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();
//        https://news.yahoo.com/rss/

        XMLTree xml = new XMLTree1(url);
        XMLTree channel = xml.child(0);

        out.println("Title: " + channel.child(getChildElement(channel, "title"))
                .child(0).label());
        out.println("Link: " + channel.child(getChildElement(channel, "link"))
                .child(0).label());
        out.println("Description: "
                + channel.child(getChildElement(channel, "description"))
                        .child(0).label());

        /*
         * TODO: #4 - for each item, output title (or description, if title is
         * not available) and link (if available)
         */
//        processItem(channel.child(getChildElement(channel, "item")), out);

        int numberOfItems = 0;
        for (int i = 0; i < channel.numberOfChildren(); i++) {
            if (channel.child(i).label().equals("item")) {
                processItem(channel.child(i), out);
                numberOfItems++;
            }
        }
        out.println(numberOfItems);

        in.close();
        out.close();
    }

}
