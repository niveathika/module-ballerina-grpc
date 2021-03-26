/*
 *  Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.ballerinalang.net.grpc.builder.syntaxtree;

import io.ballerina.compiler.syntax.tree.AbstractNodeFactory;
import io.ballerina.compiler.syntax.tree.Node;
import io.ballerina.compiler.syntax.tree.NodeFactory;
import io.ballerina.compiler.syntax.tree.NodeList;
import io.ballerina.compiler.syntax.tree.RecordTypeDescriptorNode;
import io.ballerina.compiler.syntax.tree.TypeDescriptorNode;
import org.ballerinalang.net.grpc.builder.constants.SyntaxTreeConstants;

import static org.ballerinalang.net.grpc.builder.syntaxtree.TypeDescriptor.getBuiltinSimpleNameReferenceNode;
import static org.ballerinalang.net.grpc.builder.syntaxtree.TypeDescriptor.getParameterizedTypeDescriptorNode;

public class Record {

    private NodeList<Node> fields;

    public Record() {
        fields = NodeFactory.createEmptyNodeList();
    }

    public RecordTypeDescriptorNode getRecordTypeDescriptorNode() {
        return NodeFactory.createRecordTypeDescriptorNode(
                SyntaxTreeConstants.SYNTAX_TREE_KEYWORD_RECORD,
                SyntaxTreeConstants.SYNTAX_TREE_BODY_START_DELIMITER,
                fields,
                null,
                SyntaxTreeConstants.SYNTAX_TREE_BODY_END_DELIMITER
        );
    }

    public void addStringField(String fieldName) {
        fields = fields.add(NodeFactory.createRecordFieldNode(
                null,
                null,
                getBuiltinSimpleNameReferenceNode("string"),
                AbstractNodeFactory.createIdentifierToken(fieldName),
                null,
                SyntaxTreeConstants.SYNTAX_TREE_SEMICOLON
        ));
    }

    public void addMapField(String fieldName, TypeDescriptorNode descriptorNode) {
        fields = fields.add(NodeFactory.createRecordFieldNode(
                null,
                null,
                getParameterizedTypeDescriptorNode("map", descriptorNode),
                AbstractNodeFactory.createIdentifierToken(fieldName),
                null,
                SyntaxTreeConstants.SYNTAX_TREE_SEMICOLON
        ));
    }
}
