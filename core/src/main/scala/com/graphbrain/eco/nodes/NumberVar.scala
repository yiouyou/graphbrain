package com.graphbrain.eco.nodes

import com.graphbrain.eco.{Contexts, NodeType}

class NumberVar(name: String, var value: Double) extends VarNode(name) {
  override def ntype = NodeType.Number
  override def numberValue(ctxts: Contexts) = value

  override def equals(obj:Any) = obj match {
    case n: NumberVar => n.name == name
    case _ => false
  }
}