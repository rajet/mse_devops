FROM alpine:latest
WORKDIR /data
RUN apk add --no-cache coreutils
COPY update.sh /update.sh
RUN chmod +x /update.sh
CMD ["/update.sh"]
