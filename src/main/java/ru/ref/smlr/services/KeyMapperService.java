package ru.ref.smlr.services;

import java.util.Objects;

public interface KeyMapperService {

    interface Get {
        class Link implements Get{
            private String link;

            public Link() {
            }

            public Link(String link){
                this.link = link;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Link link1 = (Link) o;
                return Objects.equals(link, link1.link);
            }

            @Override
            public int hashCode() {

                return Objects.hash(link);
            }
        }
        class NotFound implements Get{
            private String key;

            public NotFound() {
            }

            public NotFound(String key){
                this.key = key;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                NotFound notFound = (NotFound) o;
                return Objects.equals(key, notFound.key);
            }

            @Override
            public int hashCode() {

                return Objects.hash(key);
            }
        }
    }

    String add(String key);
    Get getLink(String key);
}
