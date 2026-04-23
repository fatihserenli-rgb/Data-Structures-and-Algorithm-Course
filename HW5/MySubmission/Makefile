# Makefile

SRC_DIR = src
BUILD_DIR = build
SRC_FILES = $(wildcard $(SRC_DIR)/*.java)
SOURCE_LIST = source.txt
MAIN_CLASS = Main

.PHONY: build run clean

build:
	@echo $(SRC_FILES) > $(SOURCE_LIST)
	@mkdir -p $(BUILD_DIR)
	@javac -d $(BUILD_DIR) @$(SOURCE_LIST)

run:
	java -cp $(BUILD_DIR) $(MAIN_CLASS) $(CONFIG) $(ARGS)

clean:
	@rm -f $(SOURCE_LIST)
	@rm -rf $(BUILD_DIR)

