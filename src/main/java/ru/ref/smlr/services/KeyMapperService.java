package ru.ref.smlr.services;

import java.util.Objects;

public interface KeyMapperService {

    interface Add {
        class Success implements Add{
            private String key;
            private String link;

            public Success() {
            }

            public Success(String key, String link){
                this.key = key;
                this.link = link;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
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
                Success success = (Success) o;
                return Objects.equals(key, success.key) &&
                        Objects.equals(link, success.link);
            }

            @Override
            public int hashCode() {

                return Objects.hash(key, link);
            }
        }
        class AlreadyExist implements Add{
            private String key;

            public AlreadyExist() {
            }

            public AlreadyExist(String key){
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
                AlreadyExist that = (AlreadyExist) o;
                return Objects.equals(key, that.key);
            }

            @Override
            public int hashCode() {

                return Objects.hash(key);
            }
        }
    }

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

    Add add(String key, String link);
    Get getLink(String key);
}
